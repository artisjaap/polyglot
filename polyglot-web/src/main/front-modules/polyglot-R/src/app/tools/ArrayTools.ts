export class ArrayTools {
  static copy(array: any[]): any[] {
    const result = [];
    array.forEach(e => result.push(e))
    return result;

  }
}
