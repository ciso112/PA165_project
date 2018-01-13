import {Component, Input} from '@angular/core';
import {AuthenticationService} from '../_services/authentication.service';
import {Router} from '@angular/router';
import {LoginEventsService} from '../_services/login-events.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {

  collapse = true;
  userLoggedIn: boolean;
  userIsAdmin: boolean;

  @Input() title: string;

  constructor(
    private authService: AuthenticationService,
    private loginEvents: LoginEventsService,
    private router: Router,
  ) {
    this.userLoggedIn = this.authService.isUserLoggedIn();
    this.loginEvents.changeHappened.subscribe(
      () => {
        this.userIsAdmin = this.authService.isUserAdmin();
        this.userLoggedIn = this.authService.isUserLoggedIn();
      })
  }

  logout() {
    this.authService.logout();
    this.loginEvents.loginStateChanged();
    this.router.navigateByUrl('/login');
  }

}
