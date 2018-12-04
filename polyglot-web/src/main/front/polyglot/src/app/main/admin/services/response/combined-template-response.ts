import {TemplateCodeResponse} from "./template-code-response";

export interface CombinedTemplateResponse {
  language:string;
  templates:TemplateCodeResponse[];
  id:string;
  code:string;
  active:boolean;
  createdOn:string;
}
