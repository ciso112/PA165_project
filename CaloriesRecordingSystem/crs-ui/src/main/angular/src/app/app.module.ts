import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { NgxMyDatePickerModule } from 'ngx-mydatepicker';
import { AngularDateTimePickerModule } from 'angular2-datetimepicker';

import { AppComponent } from './app.component';
import { FormsModule} from '@angular/forms';
import { ActivityDetailComponent } from './activity-detail/activity-detail.component';
import { ActivityListComponent } from './activity-list/activity-list.component';
import {ActivityService} from './_services/activity.service';
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { RecordListComponent } from './record-list/record-list.component';
import {RecordService} from './_services/record.service';
import { RecordDetailComponent } from './record-detail/record-detail.component';
import { WrongPathComponent } from './wrong-path/wrong-path.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { CategoryCheckboxesComponent } from './category-checkboxes/category-checkboxes.component';
import {CategoryService} from './_services/category.service';
import { CategoryDetailComponent } from './category-detail/category-detail.component';
import { ActivitiesComponent } from './activities/activities.component';
import { CategoryListComponent } from './category-list/category-list.component';
import { SettingsComponent } from './settings/settings.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegisterFormComponent } from './register-form/register-form.component';
import { ValidateEmailDirective } from './_validators/validate-email.directive';
import { ValidateLengthDirective } from './_validators/validate-length.directive';
import { NumberMinMaxDirective } from './_validators/number-min-max.directive';
import { ActivityFormComponent } from './activity-form/activity-form.component';
import { BurnedCaloriesListComponent } from './burned-calories-list/burned-calories-list.component';
import { SearchBarComponent } from './search-bar/search-bar.component';
import {AuthenticationService} from './_services/authentication.service';
import {JwtInterceptorProvider} from './_http-helpers/JwtInterceptor';
import {UserLoggedInGuard} from './_guards/UserLoggedInGuard';
import {UserNotLoggedInGuard} from './_guards/UserNotLoggedInGuard';
import {LoginEventsService} from './_services/login-events.service';
import {UserIsAdminGuard} from './_guards/UserIsAdminGuard';
import { ActivityDetailFormComponent } from './activity-detail-form/activity-detail-form.component';
import { ActivityDetailStaticComponent } from './activity-detail-static/activity-detail-static.component';
import {SettingsService} from './_services/settings.service';
import {RecordFormComponent} from './record-form/record-form.component';
import { TrackingSettingsComponent } from './tracking-settings/tracking-settings.component';
import { UserListComponent } from './user-list/user-list.component';
import {UserService} from "./_services/user.service";
import { UserRecordsComponent } from './user-records/user-records.component';

@NgModule({
  declarations: [
    AppComponent,
    ActivityDetailComponent,
    ActivityListComponent,
    RecordListComponent,
    RecordDetailComponent,
    WrongPathComponent,
    NavBarComponent,
    CategoryCheckboxesComponent,
    CategoryDetailComponent,
    ActivitiesComponent,
    CategoryListComponent,
    SettingsComponent,
    LoginFormComponent,
    RegisterFormComponent,
    ValidateEmailDirective,
    ValidateLengthDirective,
    NumberMinMaxDirective,
    ActivityFormComponent,
    BurnedCaloriesListComponent,
    SearchBarComponent,
    ActivityDetailFormComponent,
    ActivityDetailStaticComponent,
    RecordFormComponent,
    TrackingSettingsComponent,
    UserListComponent,
    UserRecordsComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgxMyDatePickerModule.forRoot(),
    AngularDateTimePickerModule,
  ],
  providers: [
    ActivityService,
    RecordService,
    CategoryService,
    AuthenticationService,
    SettingsService,
    JwtInterceptorProvider,
    UserLoggedInGuard,
    UserNotLoggedInGuard,
    UserIsAdminGuard,
    LoginEventsService,
    UserService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
