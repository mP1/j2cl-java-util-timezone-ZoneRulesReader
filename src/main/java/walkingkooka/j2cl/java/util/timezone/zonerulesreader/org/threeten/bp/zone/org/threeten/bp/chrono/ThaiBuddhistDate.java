/*
 * Copyright (c) 2007-present, Stephen Colebourne & Michael Nascimento Santos
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * Neither the name of JSR-310 nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.chrono;

import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.Clock;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.DateTimeException;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.LocalDate;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.LocalTime;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.Period;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.ZoneId;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.jdk8.Jdk8Methods;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.ChronoField;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalAccessor;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalAdjuster;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalAmount;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalField;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalQuery;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.TemporalUnit;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.ValueRange;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

import static walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.chrono.ThaiBuddhistChronology.YEARS_DIFFERENCE;
import static walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.ChronoField.DAY_OF_MONTH;
import static walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.ChronoField.MONTH_OF_YEAR;
import static walkingkooka.j2cl.java.util.timezone.zonerulesreader.org.threeten.bp.zone.org.threeten.bp.temporal.ChronoField.YEAR;

/**
 * A date in the Thai Buddhist calendar system.
 * <p>
 * This date operates using the {@linkplain ThaiBuddhistChronology Thai Buddhist calendar}.
 * This calendar system is primarily used in Thailand.
 * Dates are aligned such that {@code 2484-01-01 (Buddhist)} is {@code 1941-01-01 (ISO)}.
 *
 * <h3>Specification for implementors</h3>
 * This class is immutable and thread-safe.
 */
public final class ThaiBuddhistDate
        extends ChronoDateImpl<ThaiBuddhistDate>
        implements Serializable {

    /**
     * Serialization version.
     */
    private static final long serialVersionUID = -8722293800195731463L;

    /**
     * The underlying date.
     */
    private final LocalDate isoDate;

    //-----------------------------------------------------------------------
    /**
     * Obtains the current {@code ThaiBuddhistDate} from the system clock in the default time-zone.
     * <p>
     * This will query the {@link Clock#systemDefaultZone() system clock} in the default
     * time-zone to obtain the current date.
     * <p>
     * Using this method will prevent the ability to use an alternate clock for testing
     * because the clock is hard-coded.
     *
     * @return the current date using the system clock and default time-zone, not null
     */
    public static ThaiBuddhistDate now() {
        return now(Clock.systemDefaultZone());
    }

    /**
     * Obtains the current {@code ThaiBuddhistDate} from the system clock in the specified time-zone.
     * <p>
     * This will query the {@link Clock#system(ZoneId) system clock} to obtain the current date.
     * Specifying the time-zone avoids dependence on the default time-zone.
     * <p>
     * Using this method will prevent the ability to use an alternate clock for testing
     * because the clock is hard-coded.
     *
     * @param zone  the zone ID to use, not null
     * @return the current date using the system clock, not null
     */
    public static ThaiBuddhistDate now(ZoneId zone) {
        return now(Clock.system(zone));
    }

    /**
     * Obtains the current {@code ThaiBuddhistDate} from the specified clock.
     * <p>
     * This will query the specified clock to obtain the current date - today.
     * Using this method allows the use of an alternate clock for testing.
     * The alternate clock may be introduced using {@linkplain Clock dependency injection}.
     *
     * @param clock  the clock to use, not null
     * @return the current date, not null
     * @throws DateTimeException if the current date cannot be obtained
     */
    public static ThaiBuddhistDate now(Clock clock) {
        return new ThaiBuddhistDate(LocalDate.now(clock));
    }

    /**
     * Obtains a {@code ThaiBuddhistDate} representing a date in the Thai Buddhist calendar
     * system from the proleptic-year, month-of-year and day-of-month fields.
     * <p>
     * This returns a {@code ThaiBuddhistDate} with the specified fields.
     * The day must be valid for the year and month, otherwise an exception will be thrown.
     *
     * @param prolepticYear  the Thai Buddhist proleptic-year
     * @param month  the Thai Buddhist month-of-year, from 1 to 12
     * @param dayOfMonth  the Thai Buddhist day-of-month, from 1 to 31
     * @return the date in Thai Buddhist calendar system, not null
     * @throws DateTimeException if the value of any field is out of range,
     *  or if the day-of-month is invalid for the month-year
     */
    public static ThaiBuddhistDate of(int prolepticYear, int month, int dayOfMonth) {
        return ThaiBuddhistChronology.INSTANCE.date(prolepticYear, month, dayOfMonth);
    }

    /**
     * Obtains a {@code ThaiBuddhistDate} from a temporal object.
     * <p>
     * This obtains a date in the Thai Buddhist calendar system based on the specified temporal.
     * A {@code TemporalAccessor} represents an arbitrary set of date and time information,
     * which this factory converts to an instance of {@code ThaiBuddhistDate}.
     * <p>
     * The conversion typically uses the {@link ChronoField#EPOCH_DAY EPOCH_DAY}
     * field, which is standardized across calendar systems.
     * <p>
     * This method matches the signature of the functional interface {@link TemporalQuery}
     * allowing it to be used as a query via method reference, {@code ThaiBuddhistDate::from}.
     *
     * @param temporal  the temporal object to convert, not null
     * @return the date in Thai Buddhist calendar system, not null
     * @throws DateTimeException if unable to convert to a {@code ThaiBuddhistDate}
     */
    public static ThaiBuddhistDate from(TemporalAccessor temporal) {
        return ThaiBuddhistChronology.INSTANCE.date(temporal);
    }

    //-----------------------------------------------------------------------
    /**
     * Creates an instance from an ISO date.
     *
     * @param isoDate  the standard local date, validated not null
     */
    ThaiBuddhistDate(LocalDate date) {
        Jdk8Methods.requireNonNull(date, "date");
        this.isoDate = date;
    }

    //-----------------------------------------------------------------------
    @Override
    public ThaiBuddhistChronology getChronology() {
        return ThaiBuddhistChronology.INSTANCE;
    }

    @Override
    public ThaiBuddhistEra getEra() {
        return (ThaiBuddhistEra) super.getEra();
    }

    @Override
    public int lengthOfMonth() {
        return isoDate.lengthOfMonth();
    }

    @Override
    public ValueRange range(TemporalField field) {
        if (field instanceof ChronoField) {
            if (isSupported(field)) {
                ChronoField f = (ChronoField) field;
                switch (f) {
                    case DAY_OF_MONTH:
                    case DAY_OF_YEAR:
                    case ALIGNED_WEEK_OF_MONTH:
                        return isoDate.range(field);
                    case YEAR_OF_ERA: {
                        ValueRange range = YEAR.range();
                        long max = (getProlepticYear() <= 0 ? -(range.getMinimum() + YEARS_DIFFERENCE) + 1 : range.getMaximum() + YEARS_DIFFERENCE);
                        return ValueRange.of(1, max);
                    }
                }
                return getChronology().range(f);
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
        }
        return field.rangeRefinedBy(this);
    }

    @Override
    public long getLong(TemporalField field) {
        if (field instanceof ChronoField) {
            switch ((ChronoField) field) {
                case PROLEPTIC_MONTH:
                    return getProlepticMonth();
                case YEAR_OF_ERA: {
                    int prolepticYear = getProlepticYear();
                    return (prolepticYear >= 1 ? prolepticYear : 1 - prolepticYear);
                }
                case YEAR:
                    return getProlepticYear();
                case ERA:
                    return (getProlepticYear() >= 1 ? 1 : 0);
            }
            return isoDate.getLong(field);
        }
        return field.getFrom(this);
    }

    private long getProlepticMonth() {
        return getProlepticYear() * 12L + isoDate.getMonthValue() - 1;
    }

    private int getProlepticYear() {
        return isoDate.getYear() + YEARS_DIFFERENCE;
    }

    //-----------------------------------------------------------------------
    @Override
    public ThaiBuddhistDate with(TemporalAdjuster adjuster) {
        return (ThaiBuddhistDate) super.with(adjuster);
    }

    @Override
    public ThaiBuddhistDate with(TemporalField field, long newValue) {
        if (field instanceof ChronoField) {
            ChronoField f = (ChronoField) field;
            if (getLong(f) == newValue) {
                return this;
            }
            switch (f) {
                case PROLEPTIC_MONTH:
                    getChronology().range(f).checkValidValue(newValue, f);
                    return plusMonths(newValue - getProlepticMonth());
                case YEAR_OF_ERA:
                case YEAR:
                case ERA: {
                    int nvalue = getChronology().range(f).checkValidIntValue(newValue, f);
                    switch (f) {
                        case YEAR_OF_ERA:
                            return with(isoDate.withYear((getProlepticYear() >= 1 ? nvalue : 1 - nvalue)  - YEARS_DIFFERENCE));
                        case YEAR:
                            return with(isoDate.withYear(nvalue - YEARS_DIFFERENCE));
                        case ERA:
                            return with(isoDate.withYear((1 - getProlepticYear()) - YEARS_DIFFERENCE));
                    }
                }
            }
            return with(isoDate.with(field, newValue));
        }
        return field.adjustInto(this, newValue);
    }

    @Override
    public ThaiBuddhistDate plus(TemporalAmount amount) {
        return (ThaiBuddhistDate) super.plus(amount);
    }

    @Override
    public ThaiBuddhistDate plus(long amountToAdd, TemporalUnit unit) {
        return (ThaiBuddhistDate) super.plus(amountToAdd, unit);
    }

    @Override
    public ThaiBuddhistDate minus(TemporalAmount amount) {
        return (ThaiBuddhistDate) super.minus(amount);
    }

    @Override
    public ThaiBuddhistDate minus(long amountToAdd, TemporalUnit unit) {
        return (ThaiBuddhistDate) super.minus(amountToAdd, unit);
    }

    //-----------------------------------------------------------------------
    @Override
    ThaiBuddhistDate plusYears(long years) {
        return with(isoDate.plusYears(years));
    }

    @Override
    ThaiBuddhistDate plusMonths(long months) {
        return with(isoDate.plusMonths(months));
    }

    @Override
    ThaiBuddhistDate plusDays(long days) {
        return with(isoDate.plusDays(days));
    }

    private ThaiBuddhistDate with(LocalDate newDate) {
        return (newDate.equals(isoDate) ? this : new ThaiBuddhistDate(newDate));
    }

    @Override
    @SuppressWarnings("unchecked")
    public final ChronoLocalDateTime<ThaiBuddhistDate> atTime(LocalTime localTime) {
        return (ChronoLocalDateTime<ThaiBuddhistDate>) super.atTime(localTime);
    }

    @Override
    public ChronoPeriod until(ChronoLocalDate endDate) {
        Period period = isoDate.until(endDate);
        return getChronology().period(period.getYears(), period.getMonths(), period.getDays());
    }

    @Override  // override for performance
    public long toEpochDay() {
        return isoDate.toEpochDay();
    }

    //-------------------------------------------------------------------------
    @Override  // override for performance
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ThaiBuddhistDate) {
            ThaiBuddhistDate otherDate = (ThaiBuddhistDate) obj;
            return this.isoDate.equals(otherDate.isoDate);
        }
        return false;
    }

    @Override  // override for performance
    public int hashCode() {
        return getChronology().getId().hashCode() ^ isoDate.hashCode();
    }

    //-----------------------------------------------------------------------
    private Object writeReplace() {
        return new Ser(Ser.THAIBUDDHIST_DATE_TYPE, this);
    }

    void writeExternal(DataOutput out) throws IOException {
        // MinguoChrono is implicit in the THAIBUDDHIST_DATE_TYPE
        out.writeInt(this.get(YEAR));
        out.writeByte(this.get(MONTH_OF_YEAR));
        out.writeByte(this.get(DAY_OF_MONTH));
    }

    static ChronoLocalDate readExternal(DataInput in) throws IOException {
        int year = in.readInt();
        int month = in.readByte();
        int dayOfMonth = in.readByte();
        return ThaiBuddhistChronology.INSTANCE.date(year, month, dayOfMonth);
    }

}
