import {Component, OnInit} from '@angular/core';
import {UserService} from "../_services/user.service";
import {IUserShow} from "../_interfaces/IUserShow";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  usersShowCache: IUserShow[];
  usersShow: IUserShow[];

  constructor(
    private userService: UserService
  ) {}

  loadAllUsersShowFromServer() {
    this.userService
      .getAllUsersShow()
      .subscribe(usersShow => this.usersShowCache = usersShow);
  }

  getAllUsersShow() {
    this.usersShow = this.usersShowCache;
  }

  ngOnInit() {
    this.loadAllUsersShowFromServer();
    this.getAllUsersShow();
  }


}
