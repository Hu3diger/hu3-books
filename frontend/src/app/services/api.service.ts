import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Book } from '../model/book'
import { Observable } from 'rxjs';

@Injectable()
export class ApiService {

  private SERVER_URL = "http://localhost:8080/api/v1";

  constructor(private http: HttpClient ) { }

  public search(arg: String): Observable<Book[]> {
    return this.http.get<Book[]>(this.SERVER_URL + "/book?search=" + arg);
  }

  public favor(arg: Book): Observable<Book> {
    return this.http.post<Book>(this.SERVER_URL + "/book/favor", arg);
  }

  public unFavor(arg: Number): Observable<Number> {
    return this.http.delete<Number>(this.SERVER_URL + "/book/" + arg + "/unfavor");
  }

  public loadBookshelf(): Observable<Book[]> {
    return this.http.get<Book[]>(this.SERVER_URL + "/book/all");
  }
  
}
