package ru.interview.db;

/**
 * Класс представляет сущность одной записи в БД JobDir
 * Edit by Tikhonov Sergey
 */
public class JobDirEntity {
    private String depCode;
    private String depJob;
    private String Description;

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getDepJob() {
        return depJob;
    }

    public void setDepJob(String depJob) {
        this.depJob = depJob;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public int hashCode() {
        return Math.abs(depCode.hashCode() / depJob.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JobDirEntity other = (JobDirEntity) obj;
        return (other.depJob.equals(this.depJob)) && (other.depCode.equals(this.depCode));

    }
}
