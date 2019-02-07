import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../services';
import { Employee } from '../services/model/employee';


@Component({
  selector: 'adv-employees-list',
  templateUrl: './employees-list.component.html',
  providers: [EmployeeService],
  styles: []
})
export class EmployeesListComponent implements OnInit {

  numberOfEmployees: number;
  employees: Employee[];

  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
    this.employeeService.countEmployees().subscribe(numberOfEmployees => this.numberOfEmployees = numberOfEmployees);
    this.employeeService.getAllEmployees().subscribe(employees => this.employees = employees);
  }

}
