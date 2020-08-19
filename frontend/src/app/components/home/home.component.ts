import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service'
import { Book } from '../../model/book';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  books: Book[];
  loading: Boolean;
  searchWord: String = '';

  constructor(
    private service : ApiService,
    private logger: ToastrService
  ) { }

  ngOnInit(): void {
    this.loading = false;
  }

  public search() {
    if(this.searchWord == ''){
      this.logger.warning("You must enter a keyword to search a book", "Attention!")
    } else {
      this.loading = true;
      this.books = [];
      this.service.search(this.searchWord).subscribe((books: Book[]) => {
        this.books = books;
        this.loading = false;
      });
    }
  }

  public getImageSrc(book: Book): String{
    return book.volumeInfo.imageLinks != undefined ? book.volumeInfo.imageLinks.smallThumbnail : ''
  }

  public favor(book: Book) {
    book.volumeInfo.flagFav = book.volumeInfo.flagFav == 1 ? 0 : 1;

    if (book.volumeInfo.flagFav == 1){
      this.service.favor(book).subscribe((retBook: Book) => {
        this.logger.success("Book " + book.volumeInfo.title + " favored!")
            book._id = retBook._id;
      })
    } else {
      this.service.unFavor(book._id).subscribe((status: Number) => {
          this.logger.success("Book removed from the bookshelf!");
      })
    }
  }

}
