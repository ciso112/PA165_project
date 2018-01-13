import {Component, OnInit} from '@angular/core';
import {ActivityService} from '../_services/activity.service';
import {ActivatedRoute} from '@angular/router';
import { Location } from '@angular/common';
import {ActivityDetail} from '../_classes/ActivityDetail';
import {BurnedCalories} from '../_classes/BurnedCalories';
import {ICategory} from '../_interfaces/ICategory';
import {CategoryService} from '../_services/category.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-activity-detail-form',
  templateUrl: './activity-detail-form.component.html',
  styleUrls: ['./activity-detail-form.component.css']
})
export class ActivityDetailFormComponent implements OnInit {

  id: number;
  activity: ActivityDetail;
  categories: ICategory[];

  constructor(
    private activityService: ActivityService,
    private categoryService: CategoryService,
    private location: Location,
    private route: ActivatedRoute,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.getActivity();
    this.getAllCategories();
  }

  getAllCategories() {
    this.categoryService
      .getAllCategories()
      .subscribe(categories => {
        this.categories = categories;
      });
  }

  getActivity(): void {
    this.id = +this.route.snapshot.paramMap.get('id');
    this.activityService
      .getActivityDetail(this.id)
      .subscribe(activity => {
        const { category, ...other } = activity;
        this.activity = Object.assign({ ...other, category: category.id });
      });
  }

  onAddBurnedCalories(burnedCalories: BurnedCalories) {
    const burned = this.activity.burnedCalories.filter(bc => bc.upperWeightBoundary === burnedCalories.upperWeightBoundary);

    if (burned.length > 0) {
      burned[0].amount = burnedCalories.amount;
    } else {
      this.activity.burnedCalories.push(burnedCalories);
      this.activity.burnedCalories.sort((bc1, bc2) => bc1.upperWeightBoundary - bc2.upperWeightBoundary);
    }
  }

  saveActivityDetails() {
    this.activityService
      .updateActivity(this.id, this.activity)
      .subscribe(activity => {
        const { category, ...other } = activity;
        this.activity = Object.assign({}, { ...other, category: category.id });
        this.router.navigateByUrl('');
      });
  }

  onRemoveBurnedCalories(burnedCalories: BurnedCalories) {
    this.activity.burnedCalories = this.activity.burnedCalories.filter(bc => bc !== burnedCalories);
  }
}
