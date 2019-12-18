/*
 * AdministratorUserAccountShowService.java
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorDashboardRepository repository;


	// AbstractShowService<Administrator, UserAccount> interface --------------

	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNumberOfAnnouncements", "totalNumberOfCompanyRecords", "totalNumberOfInvestorRecords", "mininumRewardOfActiveRequests", "maximumRewardOfActiveRequests", "averageRewardOfActiveRequests",
			"mininumRewardOfActiveOffers", "maximumRewardOfActiveOffers", "averageRewardOfActiveOffers", "totalNumberOfCompanyRecordsGroupedBySector", "totalNumberOfInvestorRecordsGroupedBySector", "averageNumberOfJobsPerEmployer",
			"averageNumberOfApplicationsPerEmployer", "averageNumberOfApplicationsPerWorker", "chartCompanyInvestor", "ratioOfYesFinalModeJobs", "ratioOfNoFinalModeJobs", "ratioOfPendingApplications", "ratioOfRejectedApplications",
			"ratioOfAcceptedApplications", "numberOfPendingApplicationsPerDay", "numberOfAcceptedApplicationsPerDay", "numberOfRejectedApplicationsPerDay");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result = new Dashboard();

		result.setTotalNumberOfAnnouncements(this.repository.countNumberOfAnnouncements());
		result.setTotalNumberOfCompanyRecords(this.repository.countNumberOfCompanyRecords());
		result.setTotalNumberOfInvestorRecords(this.repository.countNumberOfInvestorRecords());
		result.setMininumRewardOfActiveRequests(this.repository.mininumRewardOfActiveRequests());
		result.setMaximumRewardOfActiveRequests(this.repository.maximumRewardOfActiveRequests());
		result.setAverageRewardOfActiveRequests(this.repository.averageRewardOfActiveRequests());
		result.setMininumRewardOfActiveOffers(this.repository.mininumRewardOfActiveOffers());
		result.setMaximumRewardOfActiveOffers(this.repository.maximumRewardOfActiveOffers());
		result.setAverageRewardOfActiveOffers(this.repository.averageRewardOfActiveOffers());
		result.setAverageNumberOfJobsPerEmployer(this.repository.averageNumberOfJobsPerEmployer());
		result.setAverageNumberOfApplicationsPerEmployer(this.repository.averageNumberOfApplicationsPerEmployer());
		result.setAverageNumberOfApplicationsPerWorker(this.repository.averageNumberOfApplicationsPerWorker());
		result.setTotalNumberOfCompanyRecordsGroupedBySector(this.repository.countNumberOfCompanyRecordsGroupedBySector());
		result.setTotalNumberOfInvestorRecordsGroupedBySector(this.repository.countNumberOfInvestorRecordsGroupedBySector());

		List<String> labels = new ArrayList<>();

		for (String[] item : this.repository.countNumberOfCompanyRecordsGroupedBySector()) {
			labels.add(item[0]);
		}
		for (String[] item : this.repository.countNumberOfInvestorRecordsGroupedBySector()) {
			if (labels.contains(item[0])) {
			} else {
				labels.add(item[0]);
			}
		}

		List<String> cr_labels = new ArrayList<>();
		List<String> cr_data = new ArrayList<>();
		List<String> ir_labels = new ArrayList<>();
		List<String> ir_data = new ArrayList<>();

		for (String[] cr : this.repository.countNumberOfCompanyRecordsGroupedBySector()) {
			cr_labels.add(cr[0]);
		}

		for (String label : labels) {
			if (cr_labels.contains(label)) {
				for (String[] cr : this.repository.countNumberOfCompanyRecordsGroupedBySector()) {
					if (label.equals(cr[0])) {
						cr_data.add(cr[1]);
					}
				}
			} else {
				cr_data.add("0");
			}
		}

		for (String[] ir : this.repository.countNumberOfInvestorRecordsGroupedBySector()) {
			ir_labels.add(ir[0]);
		}

		for (String label : labels) {
			if (ir_labels.contains(label)) {
				for (String[] ir : this.repository.countNumberOfInvestorRecordsGroupedBySector()) {
					if (label.equals(ir[0])) {
						ir_data.add(ir[1]);
					}
				}
			} else {
				ir_data.add("0");
			}
		}

		List<List<String>> chart = new ArrayList<>();

		chart.add(labels);
		chart.add(cr_data);
		chart.add(ir_data);

		result.setChartCompanyInvestor(chart);

		result.setRatioOfPendingApplications(this.repository.ratioOfPendingApplications());
		result.setRatioOfAcceptedApplications(this.repository.ratioOfAcceptedApplications());
		result.setRatioOfRejectedApplications(this.repository.ratioOfRejectedApplications());
		result.setRatioOfYesFinalModeJobs(this.repository.ratioOfYesFinalModeJobs());
		result.setRatioOfNoFinalModeJobs(this.repository.ratioOfNoFinalModeJobs());

		Map<Date, Integer> mP = new HashMap<Date, Integer>();
		for (Date dP : this.repository.numberOfPendingApplicationsPerDay()) {
			//			mP.put(Date.valueOf(oP[0].toString().split(" ")[0].replace("<<", "")), Integer.valueOf(oP[1].toString()));
			//			Date dP1 = dP;
			//			DateFormatUtils.format(dP1, "yyyy-MM-dd");
			String pattern = "yyyy-MM-dd";
			DateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(dP);
			Date dP1 = new Date();
			try {
				dP1 = simpleDateFormat.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!mP.containsKey(dP1)) {
				mP.put(dP1, 1);
			} else {
				mP.replace(dP1, mP.get(dP1) + 1);
			}
		}
		result.setNumberOfPendingApplicationsPerDay(mP);

		Map<Date, Integer> mA = new HashMap<Date, Integer>();
		for (Date dA : this.repository.numberOfAcceptedApplicationsPerDay()) {
			//			mA.put(Date.valueOf(oA[0].toString().split(" ")[0].replace("<<", "")), Integer.valueOf(oA[1].toString()));
			//			Date dA1 = dA;
			//			DateFormatUtils.format(dA1, "yyyy-MM-dd");
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(dA);
			Date dA1 = new Date();
			try {
				dA1 = simpleDateFormat.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!mA.containsKey(dA1)) {
				mA.put(dA1, 1);
			} else {
				mA.replace(dA1, mA.get(dA1) + 1);
			}
		}
		result.setNumberOfAcceptedApplicationsPerDay(mA);

		Map<Date, Integer> mR = new HashMap<Date, Integer>();
		for (Date dR : this.repository.numberOfRejectedApplicationsPerDay()) {
			//			mR.put(Date.valueOf(oR[0].toString().split(" ")[0].replace("<<", "")), Integer.valueOf(oR[1].toString()));
			//			Date dR1 = dR;
			//			DateFormatUtils.format(dR1, "yyyy-MM-dd");
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(dR);
			Date dR1 = new Date();
			try {
				dR1 = simpleDateFormat.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!mR.containsKey(dR1)) {
				mR.put(dR1, 1);
			} else {
				mR.replace(dR1, mR.get(dR1) + 1);
			}
		}
		result.setNumberOfRejectedApplicationsPerDay(mR);

		return result;
	}

}
