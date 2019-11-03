import { Component, OnInit } from '@angular/core';
import {WordPairItem} from "../../polyglot-common/word-pair-list/word-pair-item";
import {LessonListItem} from "../../polyglot-common/lesson-list/lesson-list-item";
import {Router, RouterLink, RouterLinkActive} from "@angular/router";

@Component({
  selector: 'app-language-dashboard',
  templateUrl: './language-dashboard.component.html',
  styleUrls: ['./language-dashboard.component.scss']
})
export class LanguageDashboardComponent implements OnInit {
  latestWords: WordPairItem[];
  latestLessons: LessonListItem[];

  constructor(private router:Router) { }

  ngOnInit() {
    this.latestWords = [];
    this.latestWords.push({id: "1", from: "from", to: "to"});

    this.latestLessons = [];
    this.latestLessons.push({id: "1", name: "lesson1"});
    this.latestLessons.push({id: "2", name: "lesson2"});
    this.latestLessons.push({id: "3", name: "lesson3"});

  }

  openLesson(lessonListItem: LessonListItem) {

    this.router.navigate(['home','student','languages','1','lesson', lessonListItem.id]);
  }
}
