package be.artisjaap.backup.web.endpoints;

import be.artisjaap.backup.action.BackupData;
import be.artisjaap.backup.action.CollectionInfo;
import be.artisjaap.backup.action.ConfigBuilder;
import be.artisjaap.backup.action.to.BackupConfigTO;
import be.artisjaap.backup.web.endpoints.response.CollectionInfoResponse;
import be.artisjaap.common.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/backup")
public class BackupController {

    @Autowired
    private CollectionInfo collectionInfo;

    @Autowired
    private BackupData backupData;

    @Autowired
    private ConfigBuilder configBuilder;


    public BackupController(){
        System.out.println("created");
    }

    @RequestMapping(value = "/collections", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<CollectionInfoResponse>> findAllCollectionInfo(){
        List<CollectionInfoResponse> collect = collectionInfo.allCollections().stream().map(CollectionInfoResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }

    @RequestMapping(value = "/backup-all", method = RequestMethod.GET)
    public void backupAll(HttpServletResponse response) throws Exception{

        OutputStream outputStream = WebUtils.maakOutputstreamVoorFile(response, "backup", "zip");
        backupData.createZip(configBuilder.fullDbBackup(), outputStream);

        response.flushBuffer();
    }

    @RequestMapping(value = "/backup/{collectionName}", method = RequestMethod.GET)
    public void backupSingleCollection(HttpServletResponse response, @PathVariable String collectionName) throws Exception{
        OutputStream outputStream = WebUtils.maakOutputstreamVoorFile(response, "backup", "zip");
        backupData.createZip(configBuilder.singleTableBackup(collectionName), outputStream);
        response.flushBuffer();
    }




}
