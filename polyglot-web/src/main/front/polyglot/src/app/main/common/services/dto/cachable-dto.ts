export abstract class CachableDTO<T> {
  private loaded:boolean = false;
  private data:T;

  public isLoaded(): boolean{
    return this.loaded;
  }

  public getData(): T{
    return this.data;
  }

  public loadData(data:T){
    this.data = data;
    this.loaded = true;
  }

}
