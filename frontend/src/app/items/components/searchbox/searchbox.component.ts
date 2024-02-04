import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'items-searchbox',
  templateUrl: './searchbox.component.html',
  styles: ``,
})
export class SearchboxComponent {
  @Input()
  public placeholder: string = '';

  @Output()
  public onSearchInput: EventEmitter<string> = new EventEmitter();

  onSearch(searchTerm: string): void {
    this.onSearchInput.emit(searchTerm);
  }
}
