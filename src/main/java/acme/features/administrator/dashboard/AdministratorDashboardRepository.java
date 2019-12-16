/*
 * AdministratorUserAccountRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(a) from Announcement a")
	Integer countNumberOfAnnouncements();

	@Query("select count(cr) from CompanyRecord cr")
	Integer countNumberOfCompanyRecords();

	@Query("select count(ir) from InvestorRecord ir")
	Integer countNumberOfInvestorRecords();

	@Query("select min(r.reward.amount) from Requests r where TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Double mininumRewardOfActiveRequests();

	@Query("select max(r.reward.amount) from Requests r where TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Double maximumRewardOfActiveRequests();

	@Query("select avg(r.reward.amount) from Requests r where TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Double averageRewardOfActiveRequests();

	@Query("select min(o.rewardMin.amount) from Offer o where TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Double mininumRewardOfActiveOffers();

	@Query("select max(o.rewardMax.amount) from Offer o where TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Double maximumRewardOfActiveOffers();

	@Query("select avg((o.rewardMax.amount + o.rewardMin.amount)/2) from Offer o where TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Double averageRewardOfActiveOffers();
	
	@Query("select avg(select count(j) from Job j where j.employer.id=e.id) from Employer e")
	Double averageNumberOfJobsPerEmployer();

	@Query("select avg(select count(a) from Application a where exists(select j from Job j where j.employer.id=e.id and a.job.id=j.id)) from Employer e")
	Double averageNumberOfApplicationsPerEmployer();

	@Query("select avg(select count(a) from Application a where a.worker.id=w.id) from Worker w")
	Double averageNumberOfApplicationsPerWorker();

	@Query("select cr.sector, count(cr) from CompanyRecord cr group by cr.sector")
	String[][] countNumberOfCompanyRecordsGroupedBySector();

	@Query("select ir.sector, count(ir) from InvestorRecord ir group by ir.sector")
	String[][] countNumberOfInvestorRecordsGroupedBySector();
	
	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = 'PENDING'")
	Double ratioOfPendingApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = 'REJECTED'")
	Double ratioOfRejectedApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = 'ACCEPTED'")
	Double ratioOfAcceptedApplications();

	@Query("select 1.0 * count(j) / (select count(b) from Job b) from Job j where j.finalMode = true")
	Double ratioOfYesFinalModeJobs();

	@Query("select 1.0 * count(j) / (select count(b) from Job b) from Job j where j.finalMode = false")
	Double ratioOfNoFinalModeJobs();

	@Query("select a.lastUpdate,count(a) from Application a where a.status = 'PENDING' and TIMESTAMPDIFF(DAY, a.lastUpdate, CURRENT_DATE())<=28 group by a.lastUpdate order by a.lastUpdate asc")
	Collection<Object[]> numberOfPendingApplicationsPerDay();

	@Query("select a.lastUpdate,count(a) from Application a where a.status = 'ACCEPTED' and TIMESTAMPDIFF(DAY, a.lastUpdate, CURRENT_DATE())<=28 group by a.lastUpdate order by a.lastUpdate asc")
	Collection<Object[]> numberOfAcceptedApplicationsPerDay();

	@Query("select a.lastUpdate,count(a) from Application a where a.status = 'REJECTED' and TIMESTAMPDIFF(DAY, a.lastUpdate, CURRENT_DATE())<=28 group by a.lastUpdate order by a.lastUpdate asc")
	Collection<Object[]> numberOfRejectedApplicationsPerDay();
}
