package network.bitmesh.cloudserver.Statistics;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import network.bitmesh.cloudserver.Utils.PersistenceHelper;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

@Entity

@Table(name = "VENDORCASHOUT")
public class VendorCashOut implements Serializable
{
    @Expose
    @Id @GeneratedValue
    @Column(name = "CASH_OUT_ID", nullable=false, unique=true)
    private long cashOutId;

    @Expose
    @Column(name = "TXID")
    private String txId;

    @Expose
    @Column(name = "QUANTITY")
    private long quantity;

    @Expose
    @Column(name = "DATE")
    private Date date;

    @Expose
    @Column(name = "SUCCESSFUL")
    private boolean success;

    @Expose
    @Column(name = "REASON")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "VENDOR_ID", nullable = false)
    private Vendor vendor;

    public VendorCashOut() { }

    public VendorCashOut(String txId, long quantity, Date date, boolean success, String reason, Vendor vendor)
    {
        this.txId = txId;
        this.quantity = quantity;
        this.date = date;
        this.success = success;
        this.reason = reason;
        this.vendor = vendor;
    }

    public VendorCashOut(String txId, long quantity, boolean success, String reason, Vendor vendor)
    {
        this.txId = txId;
        this.quantity = quantity;
        this.date = Calendar.getInstance().getTime();
        this.success = success;
        this.reason = reason;
        this.vendor = vendor;
    }

    public VendorCashOut(JsonObject json, Vendor vendor)
    {
        this.txId = json.get("txId").getAsString();
        String dateString = json.get("date").getAsString();
        try
        {
            this.date = PersistenceHelper.dateFormat.parse(dateString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        this.success = json.get("success").getAsBoolean();
        this.reason = json.get("reason").getAsString();
        this.quantity = json.get("quantity").getAsLong();
        this.vendor = vendor;
    }

    public Vendor getVendor()
    {
        return vendor;
    }

    public void setVendor(Vendor vendor)
    {
        this.vendor = vendor;
    }

    public long getCashOutId()
    {
        return cashOutId;
    }

    public void setCashOutId(long cashOutId)
    {
        this.cashOutId = cashOutId;
    }

    public String getTxId()
    {
        return txId;
    }

    public void setTxId(String txId)
    {
        this.txId = txId;
    }

    public long getQuantity()
    {
        return quantity;
    }

    public void setQuantity(long quantity)
    {
        this.quantity = quantity;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return "VendorCashOut{" +
                "cashOutId=" + cashOutId +
                ", txId='" + txId + '\'' +
                ", quantity=" + quantity +
                ", date=" + date +
                '}';
    }
}
