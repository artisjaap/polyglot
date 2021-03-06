package be.artisjaap.backup.cucumber;

import be.artisjaap.backup.action.BackupData;
import be.artisjaap.backup.action.ConfigBuilder;
import be.artisjaap.backup.action.RestoreData;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class BackupStepDefinition {
    @Autowired
    private BackupData backupData;

    @Autowired
    private ConfigBuilder configBuilder;

    @Autowired
    private RestoreData restoreData;

    @Given("^A backup service$")
    public void backupService(){
        System.out.println("Backup Service");
    }
}
