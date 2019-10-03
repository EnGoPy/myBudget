package pl.wojtasik.myBudget.dao.operationData;

import java.util.Date;

public class OperationFiltering {

    private Date startDate;
    private Date endDate;

    public OperationFiltering(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
