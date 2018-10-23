import {EventEmitter, Inject, Injectable} from '@angular/core';
import {AuthenticationService} from "./authentication.service";
import {LogService} from "./log.service";
import {LanguagePairDTO} from "./dto/language-pair-dto";
import {TranslationPracticeDTO} from "./dto/translation-practice-dto";
import {HttpClient} from "@angular/common/http";
import {LanguagePairResponse} from "./response/language-pair-response";
import {Observable} from "rxjs";
import {LanguagePairRequest} from "./request/LanguagePairRequest";
import {NewTranslationsForUserRequest} from "./request/new-translations-for-user-request";
import {NewSimpleTranslationRequest} from "./request/new-simple-translation-request";
import {TranslationsForUserResponse} from "./response/translations-for-user-response";
import {map} from "rxjs/operators";
import {PracticeWordResponse} from "./response/practice-word-response";

@Injectable({
  providedIn: 'root'
})
export class ManagerTranslationService {
  public event: EventEmitter<TranslationsForUserResponse> = new EventEmitter<TranslationsForUserResponse>();

  constructor(private authenticationService: AuthenticationService,
              private logger: LogService,
              private httpClient: HttpClient,
              @Inject('API_URL') private apiurl: string) {


    this.event.subscribe((e) => {
      logger.logInfo("Catch event" + e);
    });
  }


  allLanugagePairs(): Observable<LanguagePairResponse[]> {
    const user = this.authenticationService.user;
    return this.httpClient.get<LanguagePairResponse[]>(this.apiurl + "api/translations/pairs/user/" + user.userId);
  }


  createNewLanguagePair(languagePair: LanguagePairDTO): Observable<LanguagePairResponse> {
    const body: LanguagePairRequest = new LanguagePairRequest(this.authenticationService.user.userId, languagePair.languageFrom, languagePair.languageTo);
    return this.httpClient.post<LanguagePairResponse>(this.apiurl + "api/translations/pairs/", body);
  }

  languagePairWithId(languagePairId: string): Observable<LanguagePairResponse> {
    return this.httpClient.get<LanguagePairResponse>(this.apiurl + "api/translations/pairs/" + languagePairId);
  }


  //FIXME review next two methods
  addNewWord(languagePairId: string, languageFrom: string, languageTo: string): Observable<TranslationsForUserResponse> {
    this.logger.logInfo(languagePairId + languageFrom + languageTo);
    const user = this.authenticationService.user;

    const translations = [];
    translations.push(new NewSimpleTranslationRequest(languageFrom, languageTo));
    const body: NewTranslationsForUserRequest = new NewTranslationsForUserRequest(user.userId, languagePairId, translations);
    return this.httpClient.post<TranslationsForUserResponse>(this.apiurl + "api/translations/pairs/translations", body)
      .pipe(map(t => {
        this.event.emit(t);

        return t;
      }));

  }

  public currentListToPracticeForLanguage(languagePairId: string): Observable<PracticeWordResponse[]> {
    const user = this.authenticationService.user;
    const orderType = "NORMAL";
    return this.httpClient.get<PracticeWordResponse[]>(this.apiurl + "api/translations/practice/list/"+user.userId+"/"+languagePairId+"/"+orderType);

  }

  // allWordsPaginatedAndFiltered(filters:TranslationFilter, pagination:PaginationOptions): TranslationPracticeDTO[]{
  //   return this.currentListToPracticeForLanguage(""); //FIXME replace with real call to server and filters
  // }


  /* public currentListToPracticeForLanguage(languagePairId:string): TranslationPracticeDTO[]{
     let user = this.authenticationService.user;
     this.logger.logInfo("get list for user " + user.userId + " and language pair " + languagePairId);

     if(!this.translationPracticesCached.isLoaded()){
       this.translationPracticesCached.loadData([
         {languageFrom:'nul', languageTo:'zéro'},
         {languageFrom:'een', languageTo:'un'},
         {languageFrom:'twee', languageTo:'deux'},
         {languageFrom:'drie', languageTo:'trois'},
         {languageFrom:'vier', languageTo:'quatre'},
         {languageFrom:'vijf', languageTo:'cinq'},
         {languageFrom:'zes', languageTo:'six'},
         {languageFrom:'zeven', languageTo:'sept'},
         {languageFrom:'acht', languageTo:'huit'},
         {languageFrom:'negen', languageTo:'neuf'},
         {languageFrom:'tien', languageTo:'dix'},
         {languageFrom:'elf', languageTo:'onze'},
         {languageFrom:'twaalf', languageTo:'douze'},
         {languageFrom:'melk', languageTo:'du lait'},
         {languageFrom:'water', languageTo:'l\'eau'},
         {languageFrom:'fruitsap', languageTo:'le jus de fruits'},
         {languageFrom:'het basket', languageTo:'le basket'},
         {languageFrom:'het judo', languageTo:'le judo'},
         {languageFrom:'de vriend', languageTo:'le copain'},
         {languageFrom:'de lerares', languageTo:'la prof'},
         {languageFrom:'de chocolade', languageTo:'le chocolat'},
         {languageFrom:'de dans', languageTo:'la danse'},
         {languageFrom:'de hond', languageTo:'le chien'},
         {languageFrom:'nieuw', languageTo:'nouveau'},
         {languageFrom:'sympathiek', languageTo:'sympa'},
         {languageFrom:'het is', languageTo:'c\'est'},
         {languageFrom:'het zijn', languageTo:'ce sont'},
         {languageFrom:'zijn', languageTo:'être'},
         {languageFrom:'ik hou van', languageTo:'j\'aime'},
         {languageFrom:'jij houdt van', languageTo:'tu aimes'},
         {languageFrom:'ziehier', languageTo:'voici'},
         {languageFrom:'ziedaar', languageTo:'voilà'},
         {languageFrom:'hallo', languageTo:'salut'},
         {languageFrom:'ook', languageTo:'aussi'},
         {languageFrom:'natuurlijk', languageTo:'bien sûr'},
         {languageFrom:'ik ben', languageTo:'je suis'},
         {languageFrom:'jij bent', languageTo:'tu es'},
         {languageFrom:'hij is', languageTo:'il est'},
         {languageFrom:'zij is', languageTo:'elle est'},
         {languageFrom:'wij zijn', languageTo:'nous sommes'},
         {languageFrom:'jullie zijn', languageTo:'vous êtes'},
         {languageFrom:'zij zijn (m)', languageTo:'ils ont'},
         {languageFrom:'zij zijn (v)', languageTo:'elles ont'},
         {languageFrom:'de cinema', languageTo:'le cinéma'},
         {languageFrom:'de muziek', languageTo:'la musique'},
         {languageFrom:'het voetbal', languageTo:'le foot'},
         {languageFrom:'', languageTo:''},
         {languageFrom:'', languageTo:''},
         {languageFrom:'', languageTo:''},
       ]);
     }

     return this.translationPracticesCached.getData();

   }

   languagePairWithId(languagePairId: string):LanguagePairDTO {
     let languagePairFromCache = this.languagePairsCached.findById(languagePairId);
     if(languagePairFromCache){
       return languagePairFromCache;
     }

     this.allLanugagePairs(true);
     return this.languagePairsCached.findById(languagePairId);
   }



   autogenerateLesson(lessonName:string, languagePairId:string): string{
     //return lessonID from server
     return "1a";
   }*/
}
