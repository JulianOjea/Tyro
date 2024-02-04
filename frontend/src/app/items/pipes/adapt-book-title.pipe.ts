import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'adaptBookTitle',
})
export class AdaptBookTitlePipe implements PipeTransform {
  transform(title: string): string {
    const titles = title.split('/');
    return titles[0];
  }
}
