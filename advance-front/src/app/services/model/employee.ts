/**
 * Advance management
 * Advance project
 *
 * OpenAPI spec version: 0.0.1
 * Contact: ggenier@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { Adress } from './adress';
import { Identification } from './identification';
import { Phone } from './phone';
import { Skill } from './skill';
import { Training } from './training';


/**
 * Employee resource representation
 */
export interface Employee { 
    /**
     * Technical ID
     */
    id?: number;
    /**
     * Last name of the employee
     */
    lastName: string;
    /**
     * First name of the employee
     */
    firstName: string;
    identification?: Identification;
    /**
     * Human resources ID
     */
    idEmployee?: string;
    /**
     * Date of birth
     */
    birthDate: string;
    /**
     * Type of employee
     */
    employeeType: Employee.EmployeeTypeEnum;
    /**
     * Age of the employee
     */
    age?: number;
    /**
     * List of employee skills
     */
    skills?: Array<Skill>;
    /**
     * List of training employee has been follow
     */
    trainings?: Array<Training>;
    phones?: Array<Phone>;
    adress?: Array<Adress>;
}
export namespace Employee {
    export type EmployeeTypeEnum = 'VRP' | 'ADM' | 'OUV';
    export const EmployeeTypeEnum = {
        VRP: 'VRP' as EmployeeTypeEnum,
        ADM: 'ADM' as EmployeeTypeEnum,
        OUV: 'OUV' as EmployeeTypeEnum
    };
}