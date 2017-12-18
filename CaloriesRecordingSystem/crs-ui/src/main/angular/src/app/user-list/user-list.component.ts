import {Component, OnInit} from '@angular/core';
import {UserService} from "../_services/user.service";
import {IUserDetail} from "../_interfaces/IUserDetail";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  usersShowCache: IUserDetail[];
  usersShow: IUserDetail[];

  constructor(
    private userService: UserService
  ) {}

  loadAllUsersShowFromServer() {
    this.userService
      .getAllUsers()
      .subscribe(usersShow => {
        this.usersShowCache = usersShow;
        this.getUsers();
      });
  }

  getUsers() {
    this.usersShow = this.usersShowCache;
  }

  ngOnInit() {
    this.loadAllUsersShowFromServer();

  }


}
