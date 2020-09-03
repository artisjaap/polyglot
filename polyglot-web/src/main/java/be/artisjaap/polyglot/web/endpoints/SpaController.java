package be.artisjaap.polyglot.web.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaController {

  @RequestMapping(value = "**/{path:[^\\.]*|api|socket}" , headers = {"Upgrade!=websocket"})
  public String redirect() {
    return "forward:/";
  }
}
