import { Component } from '@angular/core';
import { Book } from '../../interfaces/book.interface';
import { BookService } from '../../services/book.service';

@Component({
  selector: 'app-search-book-page',
  templateUrl: './search-book-page.component.html',
  styles: ``,
})
export class SearchBookPageComponent {
  public books: Book[] = [];

  constructor(private bookService: BookService) {}

  searchBooks(bookQuery: string): void {
    this.bookService
      .getBooks(bookQuery)
      .subscribe((response) => (this.books = response.items));
  }
}
