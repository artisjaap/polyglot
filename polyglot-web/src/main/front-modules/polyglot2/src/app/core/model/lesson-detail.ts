import {WordPair} from "./word-pair";

export interface LessonDetail {
  id: string;
  name: string;
  translations: WordPair[];
}
