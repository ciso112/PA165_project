import { Component, OnInit } from '@angular/core';
import {SettingsService} from '../_services/settings.service';
import {TrackingSettings} from '../_classes/TrackingSettings';
import {Router} from "@angular/router";

@Component({
  selector: 'app-tracking-settings',
  templateUrl: './tracking-settings.component.html',
  styleUrls: ['./tracking-settings.component.css']
})
export class TrackingSettingsComponent  implements OnInit {

  trackingSettings = new TrackingSettings();

  constructor(
    private userService: SettingsService,
    private router: Router,
  ) {
    this.trackingSettings.weeklyCaloriesGoal = 0;
  }

  getTrackingSettings() {
    this.userService
      .getTrackingSettings()
      .subscribe(trackingSettings => this.trackingSettings = trackingSettings);
  }

  saveTrackingSettings() {
    this.userService
      .saveTrackingSettings(this.trackingSettings)
      .subscribe(trackingSettings => {
        this.trackingSettings = trackingSettings;
        this.router.navigateByUrl('records');
      });

  }

  ngOnInit() {
    this.getTrackingSettings();
  }

}

