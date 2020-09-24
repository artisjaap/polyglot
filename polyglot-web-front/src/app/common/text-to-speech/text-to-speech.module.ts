import {ModuleWithProviders, NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import {TextToSpeechService} from './services/text-to-speech.service';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class TextToSpeechModule {

  static forRoot(): ModuleWithProviders<TextToSpeechModule> {
    return {
      ngModule: TextToSpeechModule,
      providers: [TextToSpeechService]
    };
  }
}
