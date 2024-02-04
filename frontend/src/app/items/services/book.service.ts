import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SearchResult } from '../interfaces/book.interface';

@Injectable({ providedIn: 'root' })
export class BookService {
  private baseUrl: string = 'https://www.googleapis.com/books/v1/volumes?q=';
  private apiKey = '';

  constructor(private http: HttpClient) {}

  getBooks(query: string): Observable<SearchResult> {
    return this.http.get<SearchResult>(
      `${this.baseUrl}${query}&key=${this.apiKey}`
    );
  }
}
