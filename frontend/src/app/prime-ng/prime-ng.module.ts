import { NgModule } from '@angular/core';
import { MenubarModule } from 'primeng/menubar';
import { InputTextModule } from 'primeng/inputtext';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';

@NgModule({
  exports: [MenubarModule, InputTextModule, CardModule, ButtonModule],
})
export class PrimeNgModule {}
