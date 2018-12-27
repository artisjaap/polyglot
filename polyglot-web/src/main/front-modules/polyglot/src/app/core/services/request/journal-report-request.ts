export class JournalReportRequest {
  constructor (public userId: string,
                public languagePairId: string,
                public lessonId: string,
                public from: string,
                public until: string) {}

}
