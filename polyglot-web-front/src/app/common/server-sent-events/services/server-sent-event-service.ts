import { Injectable } from '@angular/core';
import {Observable, ReplaySubject, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServerSentEventService {

  private connectedClientId: string;
  private eventSource: EventSource;
  private connectedState: Subject<boolean> = new ReplaySubject(1);


  constructor() { }

  public createEventSource(clientId: string): Observable<any> {
    const messageSubject: Subject<any> = new Subject<any>();
    this.connectedState.next(false);
    this.eventSource = new EventSource(`http://localhost:8080/eventSource/${clientId}`);
    this.eventSource.onopen = ev => this.connectedState.next(true);
    this.eventSource.onmessage = msg => messageSubject.next(true);

    return messageSubject.asObservable();
  }

  public connected(): Observable<boolean>{
    return this.connectedState.asObservable();
  }


}
