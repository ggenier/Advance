import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../services';
import { EmployeeService } from '../services';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'adv-employee-form',
  templateUrl: './employee-form.component.html',
  providers: [EmployeeService],
  styles: []
})
export class EmployeeFormComponent implements OnInit {

  employee: Employee;

  constructor(private router: Router, private employeeService: EmployeeService) { }

  ngOnInit() {
  }

   create() {
     // this.employee.identification.adress[0].adressType = employeeForm.get('adressType');
     console.log(this.employee);
     // this.employeeService.createEmployee(this.employee)
     // .pipe(finalize(() => this.router.navigate(['/employees-list']))).subscribe();
   }
}
