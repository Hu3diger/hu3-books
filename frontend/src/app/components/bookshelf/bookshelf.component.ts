import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service'
import { Book } from '../../model/book';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-bookshelf',
  templateUrl: './bookshelf.component.html'
})
export class BookshelfComponent implements OnInit {

  books: Book[];
  filteredBooks: Book[];
  loading: Boolean;
  searchWord: string = '';

  constructor(
    private service : ApiService,
    private logger: ToastrService
  ) { }

  ngOnInit(): void {
    this.loadBooks();
  }

  public search() {
    if(this.searchWord != ''){
      this.filteredBooks = [];
      this.loading = true;
      this.books.forEach((book: Book) => {
        if(book.volumeInfo.title.toLocaleLowerCase().includes(this.searchWord.toLocaleLowerCase())){
          this.filteredBooks.push(book);
        } else if (book.volumeInfo.description.toLocaleLowerCase().includes(this.searchWord.toLocaleLowerCase())){
          this.filteredBooks.push(book);
        }
      });
      this.loading = false;
    } else {
      this.filteredBooks = this.books;
    }
  }

  public loadBooks() {
    this.loading = true;
    this.books = [];
    this.service.loadBookshelf().subscribe((books: Book[]) => {
      this.books = books;
      this.loading = false;
      this.search();
    });
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
          let index = this.books.indexOf(book);
          this.books.splice(index, 1);
      })
    }
  }

}
