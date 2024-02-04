import { Component } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-layout-page',
  templateUrl: './layout-page.component.html',
  styles: ``,
})
export class LayoutPageComponent {
  public menuItems: MenuItem[] = [
    {
      label: 'Search',
      icon: 'pi pi-search',
      items: [
        {
          label: 'Books',
          icon: 'pi pi-book',
          routerLink: 'search-book',
        },
        {
          label: 'Films',
          icon: 'pi pi-play',
          routerLink: 'search-film',
        },
        {
          label: 'TV Series',
          icon: 'pi pi-desktop',
          routerLink: 'search-serie',
        },
      ],
    },
  ];
}
