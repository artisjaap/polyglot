import {ModuleWithProviders, NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import {ServerSentEventService} from './services/server-sent-event-service';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class ServerSentEventsModule {
  static forRoot(): ModuleWithProviders<ServerSentEventsModule> {
    return {
      ngModule: ServerSentEventsModule,
      providers: [ServerSentEventService]
    };
  }
}
