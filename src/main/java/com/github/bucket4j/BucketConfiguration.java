/*
 * Copyright 2015 Vladimir Bukhtoyarov
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.github.bucket4j;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static com.github.bucket4j.BucketExceptions.*;

public final class BucketConfiguration implements Serializable {

    public static final long INITIAL_TOKENS_UNSPECIFIED = -1;

    private final Bandwidth[] bandwidths;
    private final long[] bandwidthsInitialTokens;
    private final TimeMeter timeMeter;

    public BucketConfiguration(List<BandwidthDefinition> bandwidths, TimeMeter timeMeter) {
        if (timeMeter == null) {
            throw nullTimeMeter();
        }
        this.timeMeter = timeMeter;

        if (bandwidths.isEmpty()) {
            throw restrictionsNotSpecified();
        }
        this.bandwidths = new Bandwidth[bandwidths.size()];
        this.bandwidthsInitialTokens = new long[bandwidths.size()];
        for (int i = 0; i < bandwidths.size() ; i++) {
            this.bandwidths[i] = bandwidths.get(i).getBandwidth();
            this.bandwidthsInitialTokens[i] = bandwidths.get(i).getInitialTokens();
        }
    }

    public TimeMeter getTimeMeter() {
        return timeMeter;
    }

    public Bandwidth[] getBandwidths() {
        return bandwidths;
    }

    public long[] getBandwidthsInitialTokens() {
        return bandwidthsInitialTokens;
    }

    @Override
    public String toString() {
        return "BucketConfiguration{" +
                "bandwidths=" + Arrays.toString(bandwidths) +
                ", timeMeter=" + timeMeter +
                '}';
    }

}
