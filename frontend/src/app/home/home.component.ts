import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service'
import { Book } from '../model/book';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  book: Book;

  constructor(private apiService : ApiService) { }

  ngOnInit(): void {
    this.apiService.search("bunda").subscribe(data => {
      this.book = data;
    });
  }

}
