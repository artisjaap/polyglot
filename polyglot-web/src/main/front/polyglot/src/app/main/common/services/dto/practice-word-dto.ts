import {WordStatsDTO} from "./word-stats-dto";

export class PracticeWordDTO {
  public isReversed:boolean;
  public translationId:string;
  public question:string;
  public questionLanguage:string;
  public answer:string;
  public anwserLanguage:string;
  public wordStatsTO:WordStatsDTO;
}
