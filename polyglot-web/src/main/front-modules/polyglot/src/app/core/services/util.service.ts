import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilService {

  constructor() { }

  shuffle<T>(array: T[]): T[] {
    var copyArray = [...array];
    var currentIndex = copyArray.length, temporaryValue, randomIndex;

    // While there remain elements to shuffle...
    while (0 !== currentIndex) {

      // Pick a remaining element...
      randomIndex = Math.floor(Math.random() * currentIndex);
      currentIndex -= 1;

      // And swap it with the current element.
      temporaryValue = copyArray[currentIndex];
      copyArray[currentIndex] = copyArray[randomIndex];
      copyArray[randomIndex] = temporaryValue;
    }

    return copyArray;
  }

  removeById(array: any[], id:string) {
    let index = -1;
    for(let i = 0; i < array.length; i++){
      if(array[i].id === id){
        index = i;
        break;
      }

    }
    array.splice(index,1);

  }
}
