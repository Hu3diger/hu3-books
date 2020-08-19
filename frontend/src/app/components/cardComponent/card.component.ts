import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Book } from 'src/app/model/book';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent implements OnInit {  

  @Input() book: Book;
  @Output() favored: EventEmitter<Book> = new EventEmitter(); 

  constructor() { }

  ngOnInit(): void {
  }

  public getImageSrc(): String{
    return this.book.volumeInfo.imageLinks != undefined ? this.book.volumeInfo.imageLinks.smallThumbnail : ''
  }

  public favor() {
    this.favored.emit(this.book);
  }

}
