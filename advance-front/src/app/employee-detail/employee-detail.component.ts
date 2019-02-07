import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Employee } from '../services';
import { EmployeeService } from '../services';
import { map } from 'rxjs/operators';
import { switchMap } from 'rxjs/operators';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'adv-employee-detail',
  templateUrl: './employee-detail.component.html',
  providers: [EmployeeService],
  styles: []
})

export class EmployeeDetailComponent implements OnInit {

  employee: Employee;

  constructor(private router: Router, private employeeService: EmployeeService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap
            .pipe(
                map(paramMap => paramMap.get('idEmployee')),
                switchMap(idEmployee => this.employeeService.getEmployeeByHRId(idEmployee))

            ).subscribe(employee => this.employee = employee);
  }

  delete() {
    // Invoke REST API to delete an employee
    this.employeeService.deleteEmployeeByHRId(this.employee.idEmployee)
      .pipe(finalize(() => this.router.navigate(['/employees-list']))).subscribe();
  }
}
