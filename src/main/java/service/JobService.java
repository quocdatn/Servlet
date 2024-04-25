package service;

import model.JobModel;

import repository.JobRepository;

import java.util.List;

public class JobService {

    JobRepository jobRepository = new JobRepository();

    public List<JobModel> getAllJobs(){
        return jobRepository.getAllJobs();
    }

    public boolean deleteJobById(int id){

        return jobRepository.deleteJobById(id) > 0;
    }

    public boolean editJobById(int id, String jobName, String startDate, String endDate){
        return jobRepository.editJobById(id, jobName, startDate, endDate) > 0;
    }
}
