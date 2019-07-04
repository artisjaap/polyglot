package be.artisjaap.schedule.web.endpoints;

import be.artisjaap.schedule.action.JournalForTask;
import be.artisjaap.schedule.action.ListTasks;
import be.artisjaap.schedule.action.StartTask;
import be.artisjaap.schedule.web.endpoints.response.PlannedTaskResponse;
import be.artisjaap.schedule.web.endpoints.response.TaskJournalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/schedule")
public class ScheduleController {
    @Autowired
    private ListTasks listTasks;

    @Autowired
    private StartTask startTask;

    @Autowired
    private JournalForTask journalForTask;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<PlannedTaskResponse>> listAllTasks() {
        return ResponseEntity.ok(PlannedTaskResponse.from(listTasks.allTasks()));
    }

    @RequestMapping(value = "/start/{code}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> startTaskWithCode(@PathVariable String code) {
        startTask.withCode(code);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/journal/{code}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<TaskJournalResponse>> listAllTasks(@PathVariable String code) {
        return ResponseEntity.ok(TaskJournalResponse.from(journalForTask.withCode(code)));
    }


}
