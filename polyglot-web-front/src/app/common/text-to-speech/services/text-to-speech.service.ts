import { Injectable } from '@angular/core';
import {interval, Observable, Subject} from 'rxjs';
import {BrowserLanguage} from '../model/browser-language';

@Injectable({
  providedIn: 'root'
})
export class TextToSpeechService {
  speechMessage = new SpeechSynthesisUtterance();

  constructor() { }

  public listAvailableVoices(): BrowserLanguage[] {

    return speechSynthesis.getVoices().map((voice) =>
      new BrowserLanguage(voice.lang, voice.name)
    );
  }

  public isVoiceAvailable(language: string): boolean {
    return true;
  }

  public talk(message: string, language: string): Observable<boolean> {
    this.speechMessage.text = message;
    this.speechMessage.lang = language;
    this.speechMessage.rate = 0.8;

    window.speechSynthesis.speak(this.speechMessage);

    const subject = new Subject<boolean>();

    let counter$ = interval(1000);
    let subscription = counter$.subscribe(next => {
      if(!window.speechSynthesis.speaking) {
        subject.complete();
        subscription.unsubscribe();
      }
    });

    return subject.asObservable();
  }


}
