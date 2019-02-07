export * from './employee.service';
import { EmployeeService } from './employee.service';
export * from './trainingCenter.service';
import { TrainingCenterService } from './trainingCenter.service';
export const APIS = [EmployeeService, TrainingCenterService];
