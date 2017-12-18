import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActivityDetailComponent } from './activity-detail/activity-detail.component';
import {ActivityListComponent} from './activity-list/activity-list.component';
import {RecordListComponent} from './record-list/record-list.component';
import {RecordDetailComponent} from './record-detail/record-detail.component';
import {WrongPathComponent} from './wrong-path/wrong-path.component';
import {CategoryDetailComponent} from './category-detail/category-detail.component';
import {CategoryListComponent} from './category-list/category-list.component';
import {SettingsComponent} from './settings/settings.component';
import {LoginFormComponent} from './login-form/login-form.component';
import {RegisterFormComponent} from './register-form/register-form.component';
import {ActivityFormComponent} from './activity-form/activity-form.component';
import {UserLoggedInGuard} from './_guards/UserLoggedInGuard';
import {UserNotLoggedInGuard} from './_guards/UserNotLoggedInGuard';
import {UserIsAdminGuard} from './_guards/UserIsAdminGuard';
import {RecordFormComponent} from "./record-form/record-form.component";
import {TrackingSettingsComponent} from './tracking-settings/tracking-settings.component';
import {UserListComponent} from "./user-list/user-list.component";


const routes: Routes = [
  { path: '', redirectTo: '/activities', pathMatch: 'full' },
  { path: 'activities', component: ActivityListComponent },
  { path: 'activities/new', component: ActivityFormComponent, canActivate: [UserIsAdminGuard] },
  { path: 'activities/:id', component: ActivityDetailComponent },
  { path: 'categories', component: CategoryListComponent },
  { path: 'categories/:id', component: CategoryDetailComponent },
  { path: 'records', component: RecordListComponent, canActivate: [UserLoggedInGuard] },
  { path: 'records/new', component: RecordFormComponent, canActivate: [UserLoggedInGuard] },
  { path: 'records/:id', component: RecordDetailComponent, canActivate: [UserLoggedInGuard] },
  { path: 'records/:id/edit', component: RecordFormComponent, canActivate: [UserLoggedInGuard] },
  { path: 'settings', component: SettingsComponent, canActivate: [UserLoggedInGuard] },
  { path: 'trackingSettings', component: TrackingSettingsComponent, canActivate: [UserLoggedInGuard] },
  { path: 'login', component: LoginFormComponent, canActivate: [UserNotLoggedInGuard] },
  { path: 'register', component: RegisterFormComponent, canActivate: [UserNotLoggedInGuard] },
  { path: 'users', component: UserListComponent, canActivate: [UserIsAdminGuard] },


  //  catch all
  { path: '**', component: WrongPathComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {

}
