import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service'
import { Book } from '../../model/book';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  books: Book[];
  loading: Boolean;

  constructor(private service : ApiService) { }

  ngOnInit(): void {
    console.log("init");
    this.loading = false;
  }

  public search() {
    this.loading = true
    this.service.search("peaky blinders").subscribe((books: Book[]) => {
      console.log(books);
      this.books = books;
      this.loading = false;
    });
  }

  public getImageSrc(book: Book): String{
    return book.volumeInfo.imageLinks != undefined ? book.volumeInfo.imageLinks.smallThumbnail : ''
  }

}
