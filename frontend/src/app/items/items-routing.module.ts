import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutPageComponent } from './pages/layout-page/layout-page.component';
import { SearchBookPageComponent } from './pages/search-book-page/search-book-page.component';
import { SearchFilmPageComponent } from './pages/search-film-page/search-film-page.component';
import { SearchTVSeriePageComponent } from './pages/search-tvserie-page/search-tvserie-page.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutPageComponent,
    children: [
      {
        path: 'search-book',
        component: SearchBookPageComponent,
      },
      {
        path: 'search-film',
        component: SearchFilmPageComponent,
      },
      {
        path: 'search-serie',
        component: SearchTVSeriePageComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ItemsRoutingModule {}
