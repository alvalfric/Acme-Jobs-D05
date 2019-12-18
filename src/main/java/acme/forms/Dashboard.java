
package acme.forms;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialisation identifier --------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ----------------------------

	Integer						totalNumberOfAnnouncements;
	Integer						totalNumberOfCompanyRecords;
	Integer						totalNumberOfInvestorRecords;
	Double						mininumRewardOfActiveRequests;
	Double						maximumRewardOfActiveRequests;
	Double						averageRewardOfActiveRequests;
	Double						standardDeviationOfActiveRequests;
	Double						mininumRewardOfActiveOffers;
	Double						maximumRewardOfActiveOffers;
	Double						averageRewardOfActiveOffers;
	Double						standardDeviationOfActiveOffers;
	Double						averageNumberOfJobsPerEmployer;
	Double						averageNumberOfApplicationsPerEmployer;
	Double						averageNumberOfApplicationsPerWorker;
	String[][]					totalNumberOfCompanyRecordsGroupedBySector;
	String[][]					totalNumberOfInvestorRecordsGroupedBySector;
	List<List<String>>				chartCompanyInvestor;
	Double						ratioOfPendingApplications;
	Double						ratioOfRejectedApplications;
	Double						ratioOfAcceptedApplications;
	Double						ratioOfYesFinalModeJobs;
	Double						ratioOfNoFinalModeJobs;
	Map<Date, Integer>				numberOfPendingApplicationsPerDay;
	Map<Date, Integer>				numberOfAcceptedApplicationsPerDay;
	Map<Date, Integer>				numberOfRejectedApplicationsPerDay;
}
