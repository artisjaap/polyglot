package be.artisjaap.backup.action;

import be.artisjaap.backup.action.to.BackupCollectionConfigTO;
import be.artisjaap.backup.action.to.BackupConfigTO;
import be.artisjaap.common.utils.LocalDateUtils;
import be.artisjaap.mail.action.SendMail;
import be.artisjaap.mail.action.to.MailTO;
import be.artisjaap.mail.model.Attachment;
import com.mongodb.client.MongoCollection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class BackupData {
    private static final Logger logger = LogManager.getLogger();

    private final MongoTemplate mongoTemplate;
    private final SendMail sendMail;

    private BackupData(MongoTemplate mongoTemplate, SendMail sendMail){
        this.mongoTemplate = mongoTemplate;
        this.sendMail = sendMail;
    }

    public void backupDataToEmail(BackupConfigTO config, String email) throws IOException {
        ByteArrayOutputStream fout = new ByteArrayOutputStream();

        createZip(config, fout);
        fout.close();

        String filename = "backup_" + LocalDateUtils.nowTimestamp() + ".zip";

        MailTO.builder()
                .from("coene.stijn@gmail.com")
                .to(email)
                .subject("backup")
                .body("backup")
                .attachments(Arrays.asList(new Attachment(filename, "application/zip", fout)))
                .build();


    }

    public File backupDataToFile(BackupConfigTO config, File outfile) throws IOException {
        FileOutputStream fout = new FileOutputStream(outfile);
        createZip(config, fout);
        fout.close();
        return outfile;
    }


    public void createZip(BackupConfigTO config, OutputStream fout) throws IOException {
        ZipOutputStream zout = new ZipOutputStream(fout);

        for (BackupCollectionConfigTO bcConfig : config.getConfig()) {
            MongoCollection<Document> collection = mongoTemplate.getCollection(bcConfig.getName());

            logger.info("Backup collectie: " + bcConfig.getName() + "[" + collection.count() + "]");
            zout.putNextEntry(new ZipEntry(bcConfig.getName() + ".json"));

            List<Object> idsToRemove = new ArrayList<>();

            for (Document object : collection.find()) {
                String objectString = object.toJson();

                if (bcConfig.getClearAfterBackup()) {
                    idsToRemove.add(object.get("_id"));
                }

                zout.write(objectString.getBytes());
                zout.write('\n');
            }

            if (bcConfig.getClearAfterBackup()) {
                String idSample = idsToRemove.stream().limit(5).map(Object::toString).collect(Collectors.joining(","));
                logger.info("Remove items from collection " + bcConfig.getName() + "[" + idsToRemove.size() + "]{ "
                        + idSample + "...}");
                Query query = Query.query(Criteria.where("_id").in(idsToRemove));
                mongoTemplate.remove(query, bcConfig.getName());
            }

            idsToRemove.clear();

            zout.closeEntry();

        }

        zout.close();
    }
}
