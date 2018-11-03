import {Injectable} from '@angular/core';
import {HttpClient, HttpEventType, HttpRequest, HttpResponse} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import {st} from "@angular/core/src/render3";

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  constructor(private http:HttpClient) { }

  public upload(files: Set<File>, url:string): {[key:string]:Observable<number>} {
    const status = {};

    files.forEach(file => {
      const formData: FormData = new FormData();
      formData.append('file', file, file.name);

      const req = new HttpRequest('POST', url, formData, {reportProgress:true});

      const progress = new Subject<number>();

      this.http.request(req).subscribe(event => {
        if(event.type === HttpEventType.UploadProgress) {
          const percentDone = Math.round(100 * event.loaded / event.total);
          progress.next(percentDone);
        } else if (event instanceof HttpResponse){
          progress.complete();
        }
      })

      status[file.name] = {progress:progress.asObservable()};


    });
    return status;
  }
}
