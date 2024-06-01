import { NgModule } from '@angular/core';
import { HlmToasterComponent } from '@spartan-ng/ui-sonner-helm';


export * from './lib/hlm-toaster.component';

@NgModule({
	imports: [HlmToasterComponent],
	exports: [HlmToasterComponent],
})
export class HlmToasterModule {}
