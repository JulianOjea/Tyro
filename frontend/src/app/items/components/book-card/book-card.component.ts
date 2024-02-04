import { Component, Input } from '@angular/core';
import { Book } from '../../interfaces/book.interface';

@Component({
  selector: 'items-book-card',
  templateUrl: './book-card.component.html',
  styles: ``,
})
export class BookCardComponent {
  @Input()
  public book!: Book;
}
