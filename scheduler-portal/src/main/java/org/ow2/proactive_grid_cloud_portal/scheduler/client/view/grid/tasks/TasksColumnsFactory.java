package org.ow2.proactive_grid_cloud_portal.scheduler.client.view.grid.tasks;

import org.ow2.proactive_grid_cloud_portal.common.client.JSUtil;
import org.ow2.proactive_grid_cloud_portal.scheduler.client.Task;
import org.ow2.proactive_grid_cloud_portal.scheduler.client.view.grid.ColumnsFactory;
import org.ow2.proactive_grid_cloud_portal.scheduler.client.view.grid.GridColumns;

import com.smartgwt.client.data.Record;

/**
 * The factory to get the columns specifications, and build record acoording to this specification.
 * @author the activeeon team
 *
 */
public abstract class TasksColumnsFactory implements ColumnsFactory<Task>{

    public static GridColumns ID_ATTR = new GridColumns("id", "Id", 60, true, true);
    public static GridColumns STATUS_ATTR = new GridColumns("status", "Status", 80, true, false);
    public static GridColumns NAME_ATTR = new GridColumns("name", "Name", 100, true, false);
    public static GridColumns TAG_ATTR = new GridColumns("tag", "Tag", 100, true, false);
    public static GridColumns EXEC_DURATION_ATTR = new GridColumns("execDuration", "Duration", 80, true, false);
    public static GridColumns NODE_COUNT_ATTR = new GridColumns("nodeCount", "Nodes", 40, true, false);
    public static GridColumns EXECUTIONS_ATTR = new GridColumns("executions", "Executions", 60, true, false);
    public static GridColumns NODE_FAILURE_ATTR = new GridColumns("nodeFailure", "Failures", 60, true, false);
    public static GridColumns HOST_ATTR = new GridColumns("host", "Host", 100, true, false);
    public static GridColumns START_TIME_ATTR = new GridColumns("startTime", "Started at", 100, true, false);
    public static GridColumns FINISHED_TIME_ATTR = new GridColumns("finishedTime", "Finished at", 100, true, false);
    public static GridColumns DESCRIPTION_ATTR = new GridColumns("description", "Description", 100, true, false);
    public static GridColumns VISU_ATTR = new GridColumns("visu", " ", 20, false, false);
    
    @Override
    public GridColumns[] getColumns() {
        return new GridColumns[]{ID_ATTR, STATUS_ATTR, NAME_ATTR, TAG_ATTR, EXEC_DURATION_ATTR, NODE_COUNT_ATTR, 
                EXECUTIONS_ATTR, NODE_FAILURE_ATTR, HOST_ATTR, START_TIME_ATTR, FINISHED_TIME_ATTR,
                DESCRIPTION_ATTR, VISU_ATTR};
    }

    
    @Override
    public void buildRecord(Task item, Record record) {
        record.setAttribute(ID_ATTR.getName(), item.getId().longValue());
        String execs = (item.getMaxNumberOfExec() - item.getNumberOfExecLeft()) + " / " +
                item.getMaxNumberOfExec();
        if (item.getNumberOfExecLeft() > 0)
            execs = (item.getMaxNumberOfExec() - item.getNumberOfExecLeft() + 1) + " / " +
                    item.getMaxNumberOfExec();

        String fails = (item.getMaxNumberOfExecOnFailure() - item.getNumberOfExecOnFailureLeft()) + " / " +
                item.getMaxNumberOfExecOnFailure();

        record.setAttribute(NAME_ATTR.getName(), item.getName());
        record.setAttribute(TAG_ATTR.getName(), item.getTag());
        record.setAttribute(STATUS_ATTR.getName(), item.getStatus().toString());
        record.setAttribute(EXEC_DURATION_ATTR.getName(), item.getExecutionTime());
        record.setAttribute(EXECUTIONS_ATTR.getName(), execs);
        record.setAttribute(NODE_FAILURE_ATTR.getName(), fails);
        record.setAttribute(NODE_COUNT_ATTR.getName(), item.getNodeCount());
    }

    
    /**
     * Builds the record attribute for data that could be shown in detail section in a expandable grid.
     * @param record the record that contains the attributes.
     * @param item the item that contains the data.
     */
    protected void buildDetailsColumns(Record record, Task item){
        record.setAttribute(HOST_ATTR.getName(), (item.getHostName().equals("null") ? "" : item.getHostName()));
        record.setAttribute(DESCRIPTION_ATTR.getName(), item.getDescription());
        
        if (item.getStartTime() > 0)
            record.setAttribute(START_TIME_ATTR.getName(), JSUtil.getTime(item.getStartTime()));
        if (item.getFinishTime() > item.getStartTime())
            record.setAttribute(FINISHED_TIME_ATTR.getName(), JSUtil.getTime(item.getFinishTime()));
    }
}