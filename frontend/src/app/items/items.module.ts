import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ItemsRoutingModule } from './items-routing.module';
import { SearchBookPageComponent } from './pages/search-book-page/search-book-page.component';
import { SearchFilmPageComponent } from './pages/search-film-page/search-film-page.component';
import { SearchTVSeriePageComponent } from './pages/search-tvserie-page/search-tvserie-page.component';
import { LayoutPageComponent } from './pages/layout-page/layout-page.component';
import { PrimeNgModule } from '../prime-ng/prime-ng.module';
import { SearchboxComponent } from './components/searchbox/searchbox.component';
import { BookCardComponent } from './components/book-card/book-card.component';
import { AdaptBookTitlePipe } from './pipes/adapt-book-title.pipe';

@NgModule({
  declarations: [
    SearchBookPageComponent,
    SearchFilmPageComponent,
    SearchTVSeriePageComponent,
    LayoutPageComponent,
    SearchboxComponent,
    BookCardComponent,
    AdaptBookTitlePipe,
  ],
  imports: [CommonModule, ItemsRoutingModule, PrimeNgModule],
})
export class ItemsModule {}
